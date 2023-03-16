import { defineStore } from "pinia";

export const store = defineStore("token", {
  state: () => ({
    jwtToken: null,
    loggedInUser: "null",
  }),

  persist: {
    storage: sessionStorage, // note that data in sessionStorage is cleared when the page session ends
  },


  actions: {
    saveToken(username, token) {
        this.loggedInUser = username
        this.jwtToken = token
    },
    clearToken(){
        this.loggedInUser = null
        this.jwtToken = null
    }
  }
});
