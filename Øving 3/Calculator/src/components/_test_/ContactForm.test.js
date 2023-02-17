import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import ContactForm from '../ContactForm.vue'
import { createStore } from 'vuex'


describe('form elements', ()=>{
    var wrapper = undefined
    var store = undefined
    beforeEach(()=>{
      store = createStore({
        state () {
          return {
              name: "",
              email: ""
          }
        },
        mutations: {
          SET_NAME(state, name){
              state.name = name
          }, 
          SET_EMAIL(state, email){
              state.email = email
          }
        }
      })
      wrapper = mount(ContactForm, {
        global:{
          plugins:[store]
        }
      })
    })
    test('name label', ()=>{
      const nameLabel = wrapper.find('.name-input label')
      expect(nameLabel.text()).toBe('Name:')
    })
    test('email label', ()=>{
      const emailLabel = wrapper.find('.email-input label')
      expect(emailLabel.text()).toBe('Email:')
    })
    test('message label', ()=>{
      const messageLabel = wrapper.find('.message-input label')
      expect(messageLabel.text()).toBe('Message:')
    })
    test('button disabled', ()=>{
      const button = wrapper.find('.submit button')
      expect(button.attributes()).toHaveProperty('disabled')
    })
    
  
  })