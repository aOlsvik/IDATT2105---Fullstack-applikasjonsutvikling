import {test, expect} from 'vitest'
import { mount } from '@vue/test-utils'
import Calculator from '../Calculator.vue'

describe('Calculator', () => {
    it('renders correctly', () => {
      const wrapper = mount(Calculator)
      expect(wrapper.html()).toMatchSnapshot()
    })
  })