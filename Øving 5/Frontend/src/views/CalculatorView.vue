<script setup>
    import Calculator from '../components/Calculator.vue'
    import { store } from '../store/store'
    import { mapActions } from 'pinia';
</script>
<template>
    <header>
        <button @click="logOut()">Log out</button>
        <h1>Calculator</h1>
  </header>
    <Calculator />
</template>

<script>
export default {
    name:'Calculator',
    methods: {
        ...mapActions(store,["clearToken"]),

        logOut(){
            this.clearToken()
            this.$router.push('/')
        }
    }
}

</script>

<style scoped>
header{
    display: grid;
    grid-template-columns: 1fr 4fr 1fr;
    align-items: center;
}
header button{
    height: 40px;
    justify-self: flex-start;
    background-color: cadetblue;
    border:1px darkslateblue;
    border-style:ridge;
    color:white;
}

header button:hover{
    background-color: darkcyan;
}

header h1{
    justify-self: center;
    font-size: 80px;
}
</style>