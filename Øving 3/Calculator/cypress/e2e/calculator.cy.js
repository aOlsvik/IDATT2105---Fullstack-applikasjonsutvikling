describe('Calculator Tests', () => {
  beforeEach(()=>{
    cy.visit('/calculator')
  })
  it('calcuator loads', ()=>{
    cy.contains('Calculator')
  })
  it('calculator adds two numbers together', ()=>{
    cy.get('#current').should('have.text', '0')
    cy.get('button').contains('2').click()
    cy.get('button').contains('+').click()
    cy.get('button').contains('2').click()
    cy.get('button').contains('=').click()
    cy.get('#current').should('contain.text', '4')
  })
  it('history appears after calculation', ()=>{
    cy.get('History').should('not.exist')
    cy.get('#current')
    cy.get('button').contains('2').click()
    cy.get('button').contains('+').click()
    cy.get('button').contains('2').click()
    cy.get('button').contains('=').click()
    cy.get('#current')
    cy.contains('History')
  })
  })