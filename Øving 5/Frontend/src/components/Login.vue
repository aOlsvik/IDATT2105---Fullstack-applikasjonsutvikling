<script setup>
    import axios from 'axios'
    import router from '../routes/router.js'
    import { store } from '../store/store'
    import { mapActions } from "pinia";
    import { mapState } from "pinia"
</script>

<template>
    <body>
        <h1>{{header}}</h1>
        <form id="login" @submit.prevent="login()">
            <div class="usernameInput">
                <label for="loginUsername">Username:</label>
                <input type="text" name="username" id="loginUsername" placeholder="username" v-model="username">
            </div>
            <div class="passwordInput">
                <label for="loginPassword">Password:</label>
                <input type="password" name="password" id="loginPassword" placeholder="password" v-model="password">
            </div>
            <button type="submit" v-bind:disabled="password.length==0 || username.length==0">{{header}}</button>
        </form>
        <div class="signUp">
            <button v-on:click="displaySignUp()">{{buttonGuide}}</button>
        </div>
        <div class="errorMessage" v-if="showError">
            <p>{{ errorMessage }}</p>
        </div>
    </body>
</template>

<script>
    export default{
        data() {
            return {
                username:"",
                password:"",
                signUp: false,
                header: "Login",
                buttonGuide: "Click here to create a new account",
                showError: false,
                errorMessage: "Something went wrong"
            }
        },
        methods: {
            ...mapActions(store,["saveToken"]),

            async login() {
                //var path = "http://localhost:8080/"
                let path = "http://10.22.7.151:8080/"
                if(this.signUp){
                    path+="createUser"
                }else{
                    path+="token"
                }
                var user = {
                    username: this.username,
                    password: this.password
                }
                const config = {
                    headers: {
                        "Content-type": "application/json",
                    },
                };
                await axios.post(path,user,config).then(response=>{
                    let data = response.data;
                    if(data != null && data != '' && data != undefined){
                        this.showError = false
                        
                        if(!this.signUp){
                            this.saveToken(this.username, response.data)
                            router.push('/calculator')
                        }
                    }
                }).catch(error=>{
                    console.error(error)
                    this.showError = true
                })
            },
            displaySignUp() {
                if(!this.signUp){
                    this.signUp = true
                    this.header = "Create account"
                    this.buttonGuide = "Click here to login"
                } else{
                    this.signUp = false
                    this.header = "Login"
                    this.buttonGuide= "Click here to create a new account"
                }
            },
            refreshToken(){
                let path = "http://10.22.7.151:8080/token"
                var user = {
                    username: this.username,
                    password: this.password
                }
                const config = {
                    headers: {
                        "Content-type": "application/json",
                    },
                };
                setInterval(async () => {
                    console.log("refreshed token")
                    await axios.post(path,user,config).then(response=>{
                        let data = response.data;
                        if(data != null && data != '' && data != undefined){
                            this.saveToken(this.username, response.data)
                        }
                    }).catch(error=>{
                        console.error(error)
                    })
                }, 1000 * 60 * 5)
            }
        },
        computed: {
            ...mapState(store,["loggedInUser", "jwtToken"]),
        },
        watch: {
            token(){
                if(this.loggedInUser!==null){
                    this.refreshToken()
                }
            }
        }
    }
</script>

<style scoped>

body{
    background-color: rgba(147, 158, 158, 0.607);
    border:2px white solid;
    margin-top: 100px;
    font-size: 30px;
    width: 600px;
}

form{
    padding:50px 50px 0 50px;
    display:grid;
}

h1{
    color: #252525;
}

form label {
    margin: 10px 0;
    color:#252525   
}

form input {
    height: 30px;
}


button{
    border:0;
    background-color:cadetblue;
    color:white;
    height: 30px;
    font-size: 20px;
}

button:hover{
    background-color: darkcyan;
}

button:disabled{
    background-color: gainsboro;
    color: #252525;
}

.signUp{
    margin: 20px;
}


.usernameInput, .passwordInput {
    margin: 10px 0;
}


</style>