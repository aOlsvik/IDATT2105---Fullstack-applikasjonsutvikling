<script setup>
  import { RouterLink, RouterView } from "vue-router";
</script>

<template>
  <body>
    <nav>
      <label>Calculator App</label>
      <ul>
        <li><router-link to="/">Home</router-link></li>
        <li><router-link to="/calculator">Calculator</router-link></li>
        <li><router-link to="/reviews">Reviews</router-link></li>
      </ul>
    </nav>
    <RouterView />
  </body>
</template>

<style scoped>
  
  nav {
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #ddd;
  width: 100%;
  height: 80px;
}
label{
    font-size: 40px;
    float: left;
  } 

ul {
  display: flex;
  margin: 0;
  padding: 0;
  width: 50%;
  height: 100%;

}

li {
  flex-grow: 1;
  display: flex;
}

a {
  text-decoration: none;
  color: darkslategray;
  padding-left: 0;
  font-size: 25px;
  width: 100%;
}

a:hover {
  background-color: #eee;
}
  
  body {
    width: 100%;
    display:flex;
    flex-direction: column;
    align-content: center;
  }
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
    font-family: Lucida Console;
  }

</style>




