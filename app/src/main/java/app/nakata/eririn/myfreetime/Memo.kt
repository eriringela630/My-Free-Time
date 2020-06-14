package app.nakata.eririn.myfreetime

import android.text.Editable
import io.realm.RealmObject

open class Memo (
    open var shedule: String = ""
):RealmObject()