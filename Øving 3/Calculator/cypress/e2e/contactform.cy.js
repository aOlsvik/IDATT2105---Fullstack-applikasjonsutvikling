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
        
    })
    
})