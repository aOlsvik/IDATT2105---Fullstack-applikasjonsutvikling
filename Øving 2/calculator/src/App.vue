<script setup>
  import { RouterLink, RouterView } from "vue-router";
  import Calculator from './components/Calculator.vue'
</script>

<template>
  <body>
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/calculator">Calculator</router-link>
      <router-link to="/reviews">Reviews</router-link>
    </nav>
    <header>
    <h1>Calculator App</h1>
    </header>
    <RouterView />
  </body>

</template>

<style scoped>
  h1{
    font-size:100px;
    margin:0;
    padding:10px 0;
  }
  body {
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




