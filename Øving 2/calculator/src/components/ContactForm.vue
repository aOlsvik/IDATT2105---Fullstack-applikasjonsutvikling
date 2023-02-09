<script setup>
    import axios from "axios";
</script>

<template>
    <body>
        <form @submit.prevent="submitData()">
        <div class="name-input">
            <label for="name">Name: </label>
            <div style="display:grid; width: 100%;">
                <input type="text" id="name" v-model="formData.name" placeholder="Your name">
                <div style="border-radius:20px; height:30px; width:30px; background-color: green; margin:0 auto; text-align: center; position:absolute; justify-self: flex-end; align-self: center;">
                    <i style="color: white; lin">✓</i>
                </div>
            </div>
        </div>
        <div class="email-input">
            <label for="email">Email: </label>
            <input type="email" id="email" v-model="formData.email" placeholder="Your email"> 
        </div>
        <div class="message-input">
            <label for="message">Message: </label>
            <textarea placeholder="Write your message here" id="message" v-model="formData.message"></textarea> 
        </div>
        <div class="submit">
            <div class="card" v-if="this.submitted">
                <div style="border-radius:50px; height:50px; width:50px; background: #F8FAF5; margin:0 auto;">
                    <i class="checkmark">✓</i>
                </div>
                <h1>Success</h1> 
            </div>
            <div class="card" v-else-if="this.failed">
                <div style="border-radius:50px; height:50px; width:50px; background: #F8FAF5; margin:0 auto;">
                    <i style="color: red;" class="checkmark">X</i>
                </div>
                <h1 style="color:red">Failed</h1> 
            </div>
            <button :disabled="!validForm" type="submit">Submit</button>
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
                response: "",
                submitted: false,
                failed: false
                }
        },
        methods: {
            getData() {
                this.$store.commit('SET_NAME',this.name)
                this.$store.commit('SET_EMAIL',this.email)
            },
            submitData() {
                if(!this.validForm) return
                this.submitted = false
                this.getData()
                axios.post("http://localhost:3000/feedbacks", this.formData
                ).then(
                    this.formData.name = this.$store.state.name,
                    this.formData.email = this.$store.state.email,
                    setTimeout(()=>{
                        this.submitted = true
                        this.formData.message = ""
                        this.response = "Success"
                        }, 100)
                ).catch(error =>
                    this.failed = true,
                    this.response = error
                )
                console.log(this.response)
            },
            
        },
        computed: {
            validForm() {
                if(this.formData.name!=="" && this.formData.email !=="" && this.formData.message !== "" && this.isValidEmail) return true;
                return false
            },
            isValidEmail() {
                const emailRegex = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                return emailRegex.test(this.formData.email);
            },
            created(){
                this.inputs.name = this.$store.state.name;
                this.inputs.email = this.$store.state.email;
            }
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

        h1 {
          color: #88B04B;
          font-family: "Nunito Sans", "Helvetica Neue", sans-serif;
          font-weight: 900;
          font-size: 20px;
          width: fit-content;
          margin-left: 0.5rem;
        }
    
      i {
        color: #9ABC66;
        font-size: 25px;
        line-height: 50px;
        margin-right: 5px;
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

</style>