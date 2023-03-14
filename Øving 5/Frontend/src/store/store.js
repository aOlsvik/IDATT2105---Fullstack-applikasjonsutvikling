import { createStore } from 'vuex'

export default createStore({
  state () {
    return {
        username: ""
        }
  },
  mutations: {
    SET_USERNAME(state, username){
        state.username = username
    }
  }
})
