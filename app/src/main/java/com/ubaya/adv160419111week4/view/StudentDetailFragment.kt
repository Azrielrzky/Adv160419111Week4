package com.ubaya.adv160419111week4.view

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat.Token.fromBundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.Person.fromBundle
import androidx.lifecycle.ViewModelProvider
import com.ubaya.adv160419111week4.R
import com.ubaya.adv160419111week4.util.loadImage
import com.ubaya.adv160419111week4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.fragment_student_list.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

/**
 * A simple [Fragment] subclass.
 * Use the [StudentDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        var studentid = ""
        arguments?.let {
            studentid = StudentDetailFragmentArgs.fromBundle(requireArguments()).studentId
        }
        viewModel.fetch(studentid)

        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.studentLiveData.observe(viewLifecycleOwner) {
            val student = it
//            viewModel.studentLiveData.value?.let {student->
//                editID.setText(it.id)
//                editDOB.setText(it.dob)
//                editName.setText(it.name)
//                editPhone.setText(it.phone)
//                imageView2.loadImage(it.photoURL,progressBarPicture)
//                buttonNotif.setOnClickListener {
//                    Observable.timer(5,TimeUnit.SECONDS)
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe{
//                            Log.d("mynotif","notification delayed after 5 second")
//                            MainActivity.showNotification(student.name,"Notification created",
//                                R.drawable.ic_baseline_notifications_24)
//                        }
//
//                }
//            }
            student?.let {
                editID.setText(it.id)
                editDOB.setText(it.dob)
                editName.setText(it.name)
                editPhone.setText(it.phone)
                imageView2.loadImage(it.photoURL, progressBarPicture)
                buttonNotif.setOnClickListener {
                    Observable.timer(5, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            Log.d("mynotif", "notification delayed after 5 second")
                            /*student.name?.let { it1 ->
                                MainActivity.showNotification(
                                    it1, "Notification created",
                                    R.drawable.ic_baseline_notifications_24
                                )
                            }*/
                        }
                }

            }
        }
    }
}