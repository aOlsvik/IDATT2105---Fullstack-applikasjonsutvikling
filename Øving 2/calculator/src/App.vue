<script setup>
import Calculator from './components/Calculator.vue'
</script>

<template>
  <body>
    <header>
    <h1>Calculator App</h1>
  </header>
  
  <main>
    <Calculator />
  </main>
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




