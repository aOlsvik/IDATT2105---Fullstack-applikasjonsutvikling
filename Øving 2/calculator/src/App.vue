<script setup>
  import { RouterLink, RouterView } from "vue-router";
  
</script>

<template>
  <body>
    <nav class="navbar">
      <label>Calculator App</label>
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/calculator">Calculator</router-link></li>
        <li><router-link to="/contact">Contact</router-link></li>
      </ul>
    </nav>
    <RouterView />
  </body>
</template>


<style scoped>
  body {
    font-family:Verdana, Geneva, Tahoma, sans-serif;
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
  min-height: 500px;
}
  
  nav {
    
    display: flex;
    align-self: center;
    align-items: center;
    justify-content: center;
    background-color: #ddd;
    width: 70%;
    height: 80px;
    border-radius: 25px 40px 40px 25px;
    padding-left: 10px;
    margin-top: 10px;
  }
  label{
    font-size: 40px;
    margin-right: auto;
    margin-left: 1rem;
  } 

  ul {
    display: flex;
    justify-content: center;
    margin: 0;
    padding: 0 10px;
    height: 100%;
    background-color: rgb(134, 153, 156);
    border-radius: 40px;
    
  }

  li {
    flex-grow: 1;
    flex-wrap: wrap;
    display: flex;
    justify-content: center;
    align-content: center;
    list-style: none;
    padding: 0 5px;
    margin: 0 15px 0 15px
  }

  a {
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: whitesmoke;
    padding: 5px;
    border-radius: 5px;
  }

  a:hover, a:focus {
    background-color: #99cfe4;
    color:black;
  }


</style>




