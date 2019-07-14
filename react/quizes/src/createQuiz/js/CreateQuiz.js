import React, { Component } from 'react';
import NavBar from '../../homepage/js/NavBar'
import NewQuiz from './NewQuiz'
import { withRouter } from 'react-router-dom'

class CreateQuiz extends Component {

  constructor(props) {
      super(props);

  }
  componentDidMount() {
    console.log("componentDidMount");
  }

  componentDidUpdate(prevProps, prevState) {
      console.log("componentDidUpdate");
  }


  render() {
      return (
          <div>
            <div><NavBar></NavBar></div>
            <div><NewQuiz></NewQuiz></div>
          </div>

      );
  }
}
export default withRouter(CreateQuiz);
