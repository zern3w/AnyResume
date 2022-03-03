package com.testanymind.presentation.feature.skill

import android.app.Dialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.testanymind.presentation.R
import com.testanymind.presentation.base.DataBindingBottomSheetDialogFragment
import com.testanymind.presentation.databinding.FragmentSkillBinding
import com.testanymind.presentation.extension.observeEvent
import com.testanymind.presentation.util.KeyboardUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

class SkillBottomSheetFragment : DataBindingBottomSheetDialogFragment<FragmentSkillBinding>() {

    override fun layoutId() = R.layout.fragment_skill

    private val viewModel: SkillViewModel by viewModel()

    companion object {
        fun newInstance() = SkillBottomSheetFragment()
    }

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun start() {
        initView()
        initObserver()
        viewModel.getSkillList()
    }

    override fun onDismiss(dialog: DialogInterface) {
        // prevent accidental dismiss
        viewModel.save()
        super.onDismiss(dialog)
    }

    private fun initView() {
        viewBinding.apply {
            tvSave.setOnClickListener {
                viewModel.save()
                dismiss()
            }

            etSkill.apply {
                setOnEditorActionListener { v, actionId, _ ->
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        onActionDone(v)
                        KeyboardUtils.hideKeyboard(etSkill)
                        return@setOnEditorActionListener true
                    }
                    false
                }

                setOnKeyListener { _, keyCode, event ->
                    if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
                        if (etSkill.text.isNullOrEmpty()) {
                            if (!viewModel.isSkillsEmpty()) {
                                viewModel.removeLastSkill()
                                chipGroup.removeViewAt(chipGroup.childCount - 1)
                            }
                        }
                    }
                    false
                }
            }
        }
    }

    private fun initObserver() {
        viewModel.apply {
            observeEvent(addSkillEvent, ::addChipToGroup)
            observeEvent(initSkillsEvent) {
                for (skill in it) {
                    addChipToGroup(skill.skill)
                }
            }
        }
    }

    private fun onActionDone(v: TextView) {
        val skillText = v.text.trim().toString()
        if (skillText.isNotEmpty()) {
            v.text = null
            if (viewModel.isContainSkill(skillText).not()) viewModel.addSkill(skillText)
        }
    }

    private fun addChipToGroup(skillText: String) {
        viewBinding.apply {
            val chip = Chip(context).apply {
                text = skillText
                isClickable = true
                isCheckable = false
                isCloseIconVisible = true

                setOnCloseIconClickListener {
                    viewModel.removeSkill(skillText)
                    etSkill.apply {
                        visibility = View.VISIBLE
                        inputType = InputType.TYPE_CLASS_TEXT
                        requestFocus()
                    }
                    if (!KeyboardUtils.isShowingKeyboard) {
                        KeyboardUtils.showKeyboard(etSkill)
                    }
                    chipGroup.removeView(this)
                }
            }
            context?.let { ctx ->
                chip.apply {
                    chipBackgroundColor =
                        ColorStateList.valueOf(ContextCompat.getColor(ctx, R.color.grey_eeeeef))
                    setTextColor(Color.BLACK)
                    closeIcon = ContextCompat.getDrawable(ctx, R.drawable.ic_cross)
                }
            }
            chipGroup.addView(chip)
        }
    }
}