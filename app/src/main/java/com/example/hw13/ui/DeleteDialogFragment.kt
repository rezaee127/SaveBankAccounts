package com.example.hw13.ui

import com.example.hw13.viewModels.DialogViewModel
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels



class DeleteDialogFragment : DialogFragment() {
    private val vModel: DialogViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("آیا میخواهید که همه اطلاعات دیتابیس حذف شود؟")
                .setPositiveButton("بله",
                    DialogInterface.OnClickListener { dialog, id ->
                        vModel.delete()
                        dismiss()
                    })
                .setNegativeButton("خیر",
                    DialogInterface.OnClickListener { dialog, id ->
                        dismiss()
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}
