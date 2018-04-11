package appsinthesky.com.firebasewrite

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import appsinthesky.com.firebasewrite.model.Cake
import appsinthesky.com.firebasewrite.model.CakeModel
import com.google.android.gms.tasks.OnCompleteListener
import kotlinx.android.synthetic.main.add_cake_layout.*
import org.jetbrains.anko.toast


class AddCakeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_cake_layout)
        CakeModel

        add_cake_save.setOnClickListener { saveCake() }

    }

    private fun saveCake() {
        if (add_cake_name.text == null || add_cake_name.text.length < 0) {
            toast("please add name")
            return
        }
        if (add_cake_date_baked.text == null || add_cake_date_baked.text.length < 0) {
            toast("please add bake date")
            return
        }
        if (add_cake_expiry_date.text == null || add_cake_expiry_date.text.length < 0) {
            toast("please add expiry date")
            return
        }

        val cake = Cake(null)
        cake.name = add_cake_name.text.toString()
        cake.dateBaked = add_cake_date_baked.text.toString()
        cake.expiryDate = add_cake_expiry_date.text.toString()

        CakeModel.saveCake(cake, OnCompleteListener { task ->
            if (task.isComplete) {
                finish()
            }
        })
    }
}