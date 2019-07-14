import React, { Component } from 'react';
import '../css/createdQuiz.css'
import cookie from "react-cookies"

const createdQuizes = [
  { title: "Do I know JavaScript?" },
  { title: "Do I know what I do" }
];

class CreatedQuiz extends Component {
    constructor() {
        super();
        this.state = {
          createdQuizes: createdQuizes,
        }
    }

    componentDidMount() {
      var token = cookie.load("jwt");
      var bearer = "Bearer " + token;
      fetch('http://localhost:8080/ServletCreatedQuizzes?id=1', {
        headers: {
          'Authorization': bearer
        }
      })
        .then((response) => {
            response.json().then((data) => {
              this.setState({createdQuizes: data});
            })
          });
    }

    render() {
      return (
          <div id="createdQuizSection">
          <table id="createdQuiz">
            <tr>
              <th id="createdQuizLabel">Created Quizes :</th>
            </tr>
            {
              this.state.createdQuizes.map((crtqz) => {
                return <tr>
                <td>
                {crtqz.title}
                </td>
                </tr>
              })
            }
          </table>
        </div>
      );
    }
  }

  export default CreatedQuiz;
