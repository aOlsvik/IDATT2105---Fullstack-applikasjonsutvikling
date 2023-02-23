<template>
    <main >
        <h2>History</h2>
        <div class = "history">
        <p v-for="calculation in calculations.reverse()" class="calculation">
            {{ calculation }}
        </p>
    </div>
    </main>
</template>

<script>
    export default{
        props: {
            calculations: {
                type: Array,
                required: true
            }
        },
        data() {
            return{

            }
        },
        methods: {

        }
    }
</script>

<style scoped>
    main {
        background-color: seashell;
        margin-top: 10px;
        padding: 0 5px;
        border-radius: 10px;
        min-width: 324.33px;
    }
    .history {
        display:grid;
        grid-template-columns: 1fr 1fr;
        column-gap: 10px;
    }

    p {
        width: fit-content;
        font-size: 20px;
    }
    
</style>