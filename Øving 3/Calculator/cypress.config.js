const {defineConfig} = require("cypress")

module.exports = defineConfig({
    e2e: {
        setupNodeEvents(on,config){

        },
        e2e: {
            baseUrl: 'http://localhost:8080'
        }
    }
})