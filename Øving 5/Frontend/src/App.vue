<template>
  <body>
  <main>
    <router-view/>
  </main>
  </body>

</template>

<script >
export default {
  name: 'App'
}


</script>

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
    width:100%;
  }
  main {
    display: flex;
    flex-direction: column;
    align-items: center;
    font-family: Lucida Console;
  }
</style>




