describe('contact form tests', ()=>{
    beforeEach(()=>{
        cy.visit('/contact')
    })
    it('finds components', ()=>{
        cy.get('form').should('exist')
        cy.get('.name-input').should('exist')
        cy.get('.email-input').should('exist')
        cy.get('.message-input').should('exist')
        cy.get('.submit').should('exist')
    })

    it('submit button is disabled', ()=>{
        cy.get('#submitButton').should('be.disabled')
    })

    it('submit button is enabled after correct input', ()=>{
        cy.get('#name').type('Test E2E')
        cy.get('#email').type('test@test.com')
        cy.get('#message').type('This is a test to see if the button is not disabled')
        cy.get('#submitButton').should('not.be.disabled')
    })

    it('submit clears message', ()=>{
        cy.get('#name').type('Test Submit')
        cy.get('#email').type('test@test.com')
        const message = cy.get('#message')
        message.type('This is a test to see if a form can be submitted')
        message.invoke('val').should('equal','This is a test to see if a form can be submitted')
        cy.get('#submitButton').click()
        cy.wait(3000)
        cy.get('#message').invoke('val').should('be.empty')
    })
    it('submit returns failure when server isnt running',  ()=>{
        cy.get('#name').type('Test Submit')
        cy.get('#email').type('test@test.com')
        cy.get('#message').type('This is a test to see if submitting displays a response')
        cy.get('#submitButton').click()
        cy.wait(1000)
        cy.get('#sign').should('have.text', 'X')
        cy.get('#feedback').should('have.text', "Failed, try again")
    })
    it('submit returns success when server is running',  ()=>{
        cy.get('#name').type('Test Submit')
        cy.get('#email').type('test@test.com')
        cy.get('#message').type('This is a test to see if submitting displays a response')

        //mocker successful return
        cy.intercept("POST", "http://localhost:3000/feedbacks", {
            statusCode:201,
        }).as("submitData")

        cy.get('#submitButton').click()
        
        cy.wait("@submitData").then((intercept)=>{
            expect(intercept.response.statusCode).to.equal(201)
        })
    })
})