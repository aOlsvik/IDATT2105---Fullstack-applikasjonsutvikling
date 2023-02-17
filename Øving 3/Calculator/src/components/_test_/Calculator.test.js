import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import Calculator from '../Calculator.vue'

describe('calculatorButtons', ()=>{
  var wrapper = undefined
  beforeEach(()=>{
    wrapper = mount(Calculator)
  })

  test('button-clicks',()=>{
    const button = wrapper.find('.numbers button')
    button.trigger('click')
    expect(wrapper.emitted('click')).toBeTruthy()
  })

  test('append', ()=>{
    const button = wrapper.find('.numbers button')
    button.trigger('click')
    expect(wrapper.vm.$data.current).toBe('1')
  })

  test('result', ()=>{
    const operation = wrapper.find('.operators button')
    const button = wrapper.find('.numbers button')
    console.log(button.text)
    const equals = wrapper.findAll('.operators button').at(4)
    button.trigger('click')
    operation.trigger('click')
    button.trigger('click')
    equals.trigger('click')
    expect(wrapper.vm.$data.current).toBe('2')
  })
})