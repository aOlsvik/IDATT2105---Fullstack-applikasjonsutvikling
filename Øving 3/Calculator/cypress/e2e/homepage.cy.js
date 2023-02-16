describe('Home page loads correctly', () => {
    it('Header loads', () => {
      cy.visit('/')
      cy.contains('h1', 'Home Page')
    })
  })
 