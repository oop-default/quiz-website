import React, { Component } from 'react';
import '../css/createdQuiz.css'

const createdQuizes = [
  { quizName: "Do I know JavaScript?" },
  { quizName: "Do I know what I do" }
];

class CreatedQuiz extends Component {
    constructor() {
        super();
        this.state = {
          createdQuizes: createdQuizes,
        }
    }

    componentDidMount() {
      fetch('http://localhost:8080/ServletCreatedQuizzes?id=' + 1)
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
              <th id="createdQuizLabel">Taken Quizes :</th>
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
