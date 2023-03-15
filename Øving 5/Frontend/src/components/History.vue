<template>
    <main >
        <div class="navigate">
            <button id="prev-btn" v-if="pageNumber>0" @click="$emit('prevPage')">Previous</button>
            <p v-else></p>
            <h2>History</h2>
            <button id="next-btn" @click="$emit('nextPage')" v-if="morePages">Next</button>
        </div>
        <div class = "history">
            <p v-for="calculation in calculations" @click="$emit('historyclick', calculation)" class="calculation">
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
            },
            pageNumber: {
                type: Number,
                required:true
            },
            morePages: {
                type:Boolean
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
        cursor: pointer;
    }

    .navigate{
        display: grid;
        width:100%;
        grid-template-columns: 1fr 1fr 1fr;
    }
    
    .navigate button{
        align-self: center;
        margin-left:auto;
        height: 20px;
        width: 70px;
    }

    .navigate button:nth-child(odd){
        margin-right:auto;
    }

    .navigate button{
        background-color: cadetblue;
        border:1px darkslateblue;
        border-style:ridge;
        color:white;
    }

    .navigate button:hover{
        background-color: darkcyan;
    }
</style>