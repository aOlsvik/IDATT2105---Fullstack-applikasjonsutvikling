<script setup>
    import axios from "axios";
</script>

<template>
    <body>
        <form id="form" @submit.prevent="submitData()">
        <div class="name-input">
            <label for="name">Name: </label>
            <div class="validInput">
                <input type="text" id="name" v-model="formData.name" placeholder="Your name" @input="updateName">
                <div v-if="this.validName">
                    <i>✓</i>
                </div>
            </div>
        </div>
        <div class="email-input">
            <label for="email">Email: </label>
            <div class="validInput">
                <input type="email" id="email" v-model="formData.email" placeholder="Your email" @input="updateEmail"> 
                <div v-if="this.isValidEmail">
                    <i>✓</i>
                </div>
            </div>
        </div>
        <div class="message-input">
            <label for="message">Message: </label>
            <div class="validInput">
                <textarea placeholder="Write your message here" id="message" v-model="formData.message"></textarea> 
                <div v-if="this.validMessage" style="margin-top: 5px; align-self: flex-start;">
                    <i>✓</i>
                </div>
            </div>
        </div>
        <div class="submit"  :class="{'failure' : failed }">
            <div class="card" v-if="this.submitted">
                <div>
                    <i id="sign"> {{ submitSign }}</i>
                </div>
                <h1 id="feedback">{{ submitFeedback }}</h1> 
            </div>
            <button :disabled="!validForm" type="submit" id="submitButton" >Submit</button>
      </div>
    </form>
    </body>
</template>

<script>
    export default {
        data() {
            return {
                formData: {
                    name: "",
                    email: "",
                    message: ""
                },
                submitted: false,
                failed: false,
                submitFeedback: "",
                submitSign: ""
                }
        },
        methods: {
            getData() {
                this.$store.commit('SET_NAME',this.formData.name)
                this.$store.commit('SET_EMAIL',this.formData.email)
            },
            submitData() {
                if(!this.validForm) return
                this.submitted = false
                this.getData()
                axios.post("http://localhost:3000/feedbacks", this.formData
                ).then(response => {
                    this.formData.name = this.$store.state.name,
                    this.formData.email = this.$store.state.email,
                    this.submitted = true,
                    this.failed = false
                    this.submitResponse
                    setTimeout(() => {
                        this.formData.message = "",
                        this.submitted = false 
                    }, 3000) 
                }).catch(error =>{
                    this.submitted = true,
                    this.failed = true,
                    this.submitResponse
                    setTimeout(() => {
                        this.formData.message = ""
                        this.submitted = false 
                    }, 3000) 
                }
                )
                
                
            },
            updateName(event) {
                this.$store.commit("SET_NAME", event.target.value);
            },
            updateEmail(event) {
                this.$store.commit("SET_EMAIL", event.target.value);
            }
            
        },
        computed: {
            validForm() {
                return this.validName && this.isValidEmail && this.validMessage
            },
            isValidEmail() {
                const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return emailRegex.test(this.formData.email);
            },
            created(){
                this.inputs.name = this.$store.state.name;
                this.inputs.email = this.$store.state.email;
            },
            validName(){
                return this.formData.name.trim()!== ""
            },
            validMessage(){
                return this.formData.message.trim()!== ""
            },
            submitResponse() {
                if(!this.failed){
                    this.submitSign = "✓"
                    this.submitFeedback = "Success!"
                } else{
                    this.submitSign = "X"
                    this.submitFeedback = "Failed, try again"
                }
            }
        },
        created(){
            this.formData.name = this.$store.state.name;
            this.formData.email = this.$store.state.email;
        }
    }
</script>
<style scoped>
    .submit{
        display: flex;
    }
    .submit button{
        align-self: center;
        margin-left: auto;
    }

        .submit h1 {
          color: #88B04B;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-weight: 900;
          font-size: 20px;
          width: fit-content;
          margin-left: 0.5rem;
        }

        .submit i{
            color: #9ABC66;
            font-size: 25px;
            line-height: 50px;
            margin-right: 5px;
        }
      .failure i{
        color:red;
      }
      .failure h1{
        color:red;
      }
      .card {
        justify-self: flex-start;
        padding: 0px;
        display: flex;
        text-align: center;
      }
    body{
        align-items: center;
        
    }
    form {
        background-color: #ddd;;
        padding: 25px;
        display: grid;
        row-gap: 20px;
        width: 400px;
        border:3px whitesmoke;
        border-style: groove;
    }
    form button {
        background-color: #88B04B;
        color:white;
        border:0;
        border-radius: 5px;
        height: 50px;
        width: 75px;
        justify-self: flex-end;
    }
    form button:hover:enabled {
        background-color: cadetblue;
    }

    form button:disabled {
        background-color: grey;
        color: black;
    }
    label, input {
        display: block;
    }
    input, textarea {
        border: 1px solid grey;
        padding: 5px 1%;
        width: 98%;
        border-radius: 5px;
    }
    input:focus, textarea:focus {
        outline: none;
    }
    textarea {
        height: 100px;
        resize: none;
    }
    .name-input, .email-input, .message-input{
        display: grid;
        justify-items: center;
    }
    form label {
        justify-self: flex-start;
        margin-bottom: 5px;
    }
    input {
        height: 25px;
    }
    .validInput{
        display:grid; 
        width: 100%;
    }

    .validInput div {
        display:grid; 
        border-radius:20px; 
        height:30px; 
        width:30px; 
        background-color: #9ABC66; 
        position:absolute; 
        justify-self: 
        flex-end;
        align-self: 
        flex-start; 
        margin-right: 5px;
        align-self: center;
    }
    .validInput i {
        color: white; 
        font-size: 20px; 
        justify-self: center;
        margin-right: 3px;
        margin-top: 1px;
    }

    .submit .card div{
        border-radius:50px; 
        height:50px; 
        width:50px; 
        background: #F8FAF5; 
        margin:0 auto;
    }

</style>