package com.example.hw13.ui

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.hw13.viewModels.ViewModel

class DeleteDialogFragment : DialogFragment() {
    private val vModel: ViewModel by activityViewModels()
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage("آیا میخواهید که همه اطلاعات دیتابیس حذف شود؟")
                .setPositiveButton("بله",
                    DialogInterface.OnClickListener { dialog, id ->
                        vModel.delete()
                    })
                .setNegativeButton("خیر",
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}
