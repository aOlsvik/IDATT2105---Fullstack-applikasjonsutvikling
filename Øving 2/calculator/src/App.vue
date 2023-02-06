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
  display: flex;
  flex-direction: column;
  margin: 0;
  padding: 0;
  min-height: 500px;
}
  
  nav {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #ddd;
    width: 100%;
    height: 80px;
  }
  label{
    float: left;
    font-size: 40px;
    margin-right: auto;
    margin-left: 1rem;
  } 

  ul {
    float: right;
    display: flex;
    justify-content: center;
    align-content: center;
    margin: 0;
    padding: 0;
    height: 100%;
  }

  li {
    flex-grow: 1;
    flex-wrap: wrap;
    display: flex;
    justify-content: center;
    align-content: center;
    list-style: none;
    padding: 0 5px;
  }

  a {
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    text-decoration: none;
    color: darkslategray;
    padding: 5px;
    border-radius: 5px;
  }

  a:hover, a:focus {
    background-color: #eee;
  }


</style>




