import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import '../css/takenQuiz.css'

const takenQuizes = [
  { quizName: "Do I know JavaScript?" },
  { quizName: "Do I know what I do" }
];

class TakenQuiz extends Component {
    constructor() {
        super();
        this.state = {
          takenQuizes: takenQuizes,
        }
    }

    render() {
      return (
        <div id="takenQuizSection">
          <table id="takenQuiz">
            <tr>
              <th id="takenQuizLabel">Taken Quizes :</th>
            </tr>
            {
              this.state.takenQuizes.map((tknqz) => {
                return <tr>
                <td>
                {tknqz.quizName}
                </td>
                </tr>
              })
            }
          </table>
        </div>
      );
    }
  }

  export default TakenQuiz;
