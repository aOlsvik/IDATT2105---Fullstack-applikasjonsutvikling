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
            <button @click="operate(item)" class="grid-item operator-button" v-for="item in operators">
                {{item}}
            </button>
            <button @click="calculate()" class="grid-item equals">
                =
            </button>
        </div>
        <div class="numbers">
            <button @click="append(item)" class="grid-item" v-for="item in numbers">
                {{item}}
            </button>
        </div>
        <div class="bottom-row">
            <div class="grid-item"></div>
            <button @click="append(item)" class="grid-item" v-for="item in bottomRow">
                {{item}}
            </button>
        </div>
    </div>
    <div>
        <button @click="kaSomHelst()">click me</button>
        <p>{{ testString }}</p>
    </div>
    <History :calculations="calculations" v-if="notEmptyList" />
</template>

<script>
  export default{
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
        testString: ""
      }
    },
    methods: {
        append(number){
            if(this.current == "0" && number!='.') this.current = ""
            if(!(this.current.includes('.') && number=='.') && number!=''){
                if(!(number==='')){
                    this.current += number.toString()
                }
            }  
        },
        operate(operator){
            this.operator = operator;
            this.previous = this.current
            this.current = '0'
        },
        calculate(){
            let first = this.previous
            let opr = this.operator
            let second = this.current
            if(this.operator == '='){
                first = this.current
                opr = this.previousOperator 
                second = this.previous
                if(this.previousOperator == "+") {
                    this.answer = (parseFloat(this.current) + parseFloat(this.previous)).toFixed(4)
                }
                else if (this.previousOperator == "-"){
                    this.answer = (parseFloat(this.current) - parseFloat(this.previous)).toFixed(4)
                }  
                else if (this.previousOperator == "*"){
                    this.answer = (parseFloat(this.current) * parseFloat(this.previous)).toFixed(4)
                } 
                else if (this.previousOperator == "/"){
                    this.answer = (parseFloat(this.current) / parseFloat(this.previous)).toFixed(4)
                } 
                this.current = parseFloat(this.answer).toString()
                this.calculations.push((first + opr + second + "=" + parseFloat(this.answer).toString()))
            }else{
                this.previousOperator=this.operator
                if(this.operator == "+") {
                    this.answer = (parseFloat(this.previous) + parseFloat(this.current)).toFixed(4)
                }
                else if (this.operator == "-"){
                    this.answer = (parseFloat(this.previous) - parseFloat(this.current)).toFixed(4)
                }  
                else if (this.operator == "*"){
                    this.answer = (parseFloat(this.previous) * parseFloat(this.current)).toFixed(4)
                } 
                else if (this.operator == "/"){
                    this.answer = (parseFloat(this.previous) / parseFloat(this.current)).toFixed(4)
                } 
                this.previous = this.current
                this.current = parseFloat(this.answer).toString()
                this.calculations.push((first + opr + second + "=" + parseFloat(this.answer).toString()))
                this.operator = '='
            }
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
        async kaSomHelst(){
            const calculation = {
                equation: this.previous + this.operator + this.current
            }
            if(this.operator == "+") {
                this.testString = await(await(axios.post("http://localhost:8080/plus", calculation).catch(error => {
                console.log(error)
                }))).data
            }
            else if (this.operator == "-"){
                this.testString = await(await(axios.post("http://localhost:8080/minus",calculation).catch(error => {
                console.log(error)
                }))).data
            }  
            else if (this.operator == "*"){
                this.testString = await(await(axios.post("http://localhost:8080/multiplication",calculation).catch(error => {
                console.log(error)
                }))).data
            } 
            else if (this.operator == "/"){
                this.testString = await(await(axios.post("http://localhost:8080/division",calculation).catch(error => {
                console.log(error)
                }))).data
            } else{
                this.testString = await(await(axios.get("http://localhost:8080/").catch(error => {
                console.log(error)
                }))).data
            }
            
        }
    },
    computed: {
        notEmptyList() {
            return this.calculations.length !== 0
        }
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