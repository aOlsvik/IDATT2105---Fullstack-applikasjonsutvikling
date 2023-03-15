<script setup>
    import History from './History.vue'
    import axios from 'axios'
    
    
</script>

<template>
    <div class="grid-container">
        <div class="grid-item display">
            <p>{{ current }}</p>
        </div>
        <div class="top-row">
            <button @click="functions(item)" class="grid-item" v-for="item in topRow">
                {{item}}
            </button>
        </div>
        <div class="operators">
            <button @click="changeOperator(item)" class="grid-item operator-button" v-for="item in operators">
                {{item}}
            </button>
            <button @click="calculate()" class="grid-item equals">
                =
            </button>
        </div>
        <div class="numbers">
            <button @click="appendSymbol(item)" class="grid-item" v-for="item in numbers">
                {{item}}
            </button>
        </div>
        <div class="bottom-row">
            <div class="grid-item"></div>
            <button @click="appendSymbol(item)" class="grid-item" v-for="item in bottomRow">
                {{item}}
            </button>
        </div>
    </div>
    <History :pageNumber="pageNumber" :morePages="morePages" :calculations="calculations" v-if="notEmptyList" 
        @historyclick="(calculation)=>display(calculation)"
        @nextPage="nextPage()"
        @prevPage="prevPage()"
        @noMorePages="pageNumber=false"
    />
</template>

<script>
  export default{
    components:{
        History
    },
    data(){
      return {
        topRow: ['C', 'ANS', 'DEL'],
        operators: ['+', '-', '*', '/'],
        numbers: ["1", "2", "3", "4","5","6","7","8",'9'],
        bottomRow: ['0','.'],
        current: "0",
        operator: null,
        previous: "0",
        answer: "0",
        previousOperator: "",
        calculations: [],
        pageNumber: 0,
        morePages:true,
      }
    },
    methods: {
        appendSymbol(number){
            if(this.current == "0" && number!='.') this.current = ""
            if(!(this.current.includes('.') && number=='.') && number!=''){
                if(!(number==='')){
                    this.current += number.toString()
                }
            }  
        },
        changeOperator(operator){
            this.operator = operator;
            this.previous = this.current
            this.current = '0'
        },
        functions(func){
            if(func === "C"){
                this.current = "0"
                this.previous = '0'
            } 
            else if(func === "ANS") this.current = parseFloat(this.answer).toString()
            else if(func === "DEL"){
                if(this.current != "0") this.current = this.current.substring(0,this.current.length-1)
                if(this.current.length == 0) this.current = "0"
                if(this.current.charAt(this.current.length-1) === '.') this.current = this.current.substring(0,this.current.length-1)
            } 
        },
        async calculate(){
            let equation = {
                num1: this.previous,
                num2: this.current,
                username: this.$store.state.username
            }
            let address = "http://localhost:8080/"
            //let address = "http://10.22.7.151:8080/"
            let opr = this.operator
            if(this.operator == '='){
                equation.num1 = this.current
                opr = this.previousOperator
                equation.num2 = this.previous
            }else{
                this.previousOperator=this.operator
            }

            if(opr === "+") address+="plus"
            else if (opr === "-") address+="minus"
            else if (opr === "*") address+="multiplication"
            else if (opr === "/") address+="division"
            else if(this.operator == "="){
                if(this.previousOperator === "+") address+="plus"
                else if (this.previousOperator === "-") address+="minus"
                else if (this.previousOperator === "*") address+="multiplication"
                else if (this.previousOperator === "/") address+="division"
            }
            
            const result = await(await(axios.post(address, equation).catch(error => {
                console.log(error)
                }))).data

            this.update(result)
        },
        update(result){
            if(this.operator !== "="){
                this.operator = '='
                this.previous = this.current
            }            
            
            this.answer = result.answer.toFixed(4)
            this.current = parseFloat(this.answer).toString()
            

            if(result.num1<0) {
                result.num1 = "(" + result.num1 + ")"
            } 
            if(result.num2<0) {
                result.num2 = "(" + result.num2 + ")"
            } 
            
            let calculation = (result.num1 + result.operator + result.num2 + "=" + this.current)
            this.addCalculation(calculation)
            
            
        },
        addCalculation(calculation){
            this.calculations.unshift(calculation)
            if(this.calculations.length>10) this.calculations.pop()

        },
        display(calculation){
            let split = calculation.split('=')
            console.log(split)
            this.current = split[1]
            this.answer = this.current
        },
        goToPage(){
            let username = this.$store.state.username;
            let path = "http://localhost:8080/calculations/"
            //let path = "http://10.22.7.151:8080/calculations/"
            path+=username+"/"+this.pageNumber
            axios.get(path).then(response=>{
                if(response.data.length!=0){
                    this.morePages=true
                    this.calculations = response.data

                }else{
                    this.pageNumber--
                    this.morePages=false
                }
                
            }).catch(error=>{
                this.pageNumber--
                console.error(error)
            })
        },
        prevPage(){
            this.goToPage(this.pageNumber--)
        },
        nextPage(){
            this.goToPage(this.pageNumber++)
        }
    },
    computed: {
        notEmptyList() {
            return this.calculations.length !== 0
        }
    },
    mounted(){
        let username = this.$store.state.username;
        if (!username) {
          this.$router.push('/');
        }
        let path = "http://localhost:8080/calculations/"
        //let path = "http://10.22.7.151:8080/calculations/"
        path+=username+"/0"

        var user = {
            username: this.username
        }
        

        axios.get(path,user).then(response=>{
            this.calculations = response.data
        }).catch(error=>{
            console.error(error)
        })
    }

  }
</script>

<style scoped>
.grid-container{
  margin:0;
  padding:10px;
  display: grid;
  border: 2px black solid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  gap: 5px;
  width:fit-content;
  background-color: lightgray;
  border-radius: 10px;
}
.grid-item {
    background-color: darkslategrey;
    text-align: center;
    color:white;
    height: 50px;
    border-radius: 10px;
    min-width: 70px;
    border: 1px black solid;
}
.display{
  grid-column: span 4;
  grid-row: span 2;
  height: 70px;
  width: 95%;
  display: flex;
  align-items: center;
  justify-content: flex-end;
  margin: auto;
  font-size: 30px;
  padding: 0 2.5%;
}
.top-row{
  grid-column: span 3;
  display:grid;
  grid-template-columns: 1fr 1fr 1fr;
  grid-gap: 5px;

}
.top-row button{
    background-color: rgb(8, 84, 50);
}
.operator-button{
    background-color: rgb(8,84,50);
}
.operators {
  grid-row: span 5;
  display:flex;
  flex-direction: column;
  justify-content: space-between;
}

.numbers{
  grid-column: span 3;
  grid-row: span 3;
  display:grid;
  gap:5px;
  grid-template-columns: 1fr 1fr 1fr;
}

.bottom-row {
  grid-column: span 3;
  display:grid;
  grid-template-columns: 1fr 1fr 1fr;
  gap:5px;

}


button{
  background-color: transparent;
  width: 100%;
  height: 100%;
  color:white;
  font-size: 20px;
  border:0;
}

button:hover {
  background-color: orange;
  border-radius: 10px;
  cursor:pointer;
}

.numbers button:hover{
    background-color: cadetblue;
}
.bottom-row button:hover{
    background-color: cadetblue;
}
.equals{
    background-color: orange;
}
.equals:hover{
    background-color: rgb(213, 90, 71);
}
</style>