<script setup>
    import axios from 'axios'
    import router from '../routes/router.js'
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
            <button type="submit">{{header}}</button>
        </form>
        <div class="signUp">
            <button v-on:click="displaySignUp()">{{buttonGuide}}</button>
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
                buttonGuide: "Click here to create a new account"
            }
        },
        methods: {
            login() {
                var path = "http://localhost:8080/"
                if(this.signUp){
                    path+="createUser"
                }else{
                    path+="login"
                }
                var user = {
                    username: this.username,
                    password: this.password
                }
                axios.post(path,user).then(response=>{
                    if(response.data){
                        this.$store.commit('SET_USERNAME', this.username)
                        router.push('/calculator')
                    }else{
                        console.error("Try another username / password")
                    }
                }
                )
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
            }
        },
        computed: {
            
        }
    }
</script>

<style scoped>

body{
    background-color: blanchedalmond;
}

form{
    padding:50px;
    display:grid;
}

form label {
    margin: 10px 0;
}

form input {
    height: 20px;
}

form button {
    align-self: flex-end;
}

button{
    border:0;
    background-color:cadetblue;
    color:white;
    height: 30px;
}

button:hover{
    background-color: darkcyan;
}

.signUp{
    margin: 10px auto;
}


.usernameInput, .passwordInput {
    margin: 10px 0;
}


</style>