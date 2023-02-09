import { createStore } from 'vuex'

const store = createStore({
  state () {
    return {
        name: "",
        email: ""
    }
  },
  getters: {

  },
  mutations: {
    SET_NAME(state,name){
        state.name = name
    }, 
    SET_EMAIL(state,email){
        state.email = email
    }
  }
})

export default store

