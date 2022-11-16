package com.example.newfinal

class User {
    var id: Int = 0
    var email: String = ""
    var name: String = ""
    var password: String =""

    constructor(id: Int, name: String, email:String, password:String){
        this.id=id
        this.name=name
        this.email=email
        this.password=password
    }


}