<template>
    <body>
        <form>
        <div class="name-input">
            <label for="name">Name: </label>
            <input type="text" id="name">
        </div>
        <div class="email-input">
            <label for="email">Email: </label>
            <input type="email" id="email"> 
        </div>
        <div class="message-input">
            <label for="message">Message: </label>
            <textarea style="resize: none;" placeholder="Write your message here" id="message"></textarea> 
        </div>
        <button>Submit</button>
    </form>
    </body>
</template>
<style scoped>
    body{
        align-items: center;
        
    }
    form {
        background-color: #ddd;;
        padding: 25px;
        display: grid;
        row-gap: 20px;
        width: 400px;
    }
    form button {
        background-color: darkslategray;
        color:white;
        border:0;
        border-radius: 5px;
        height: 25px;
        width: 75px;
        justify-self: flex-end;
    }
    form button:hover {
        background-color: cadetblue;
    }
    label, input {
        display: block;
    }
    input, textarea {
        border: 0.5px solid darkgray;
        width: 100%;
    }
    textarea {
        height: 100px;
    }
    .name-input, .email-input, .message-input{
        display: grid;
        justify-items: center;
    }
    form label {
        justify-self: flex-start;
    }
    input {
        height: 25px;;
    }
</style>