import React,{Component} from 'react';
import '../css/Left.css';
import '../css/Arrows.css';
import {changeTable} from './functions';


const popQuizes = [
    {id: 1, quizName: "pirveli", creator: "me"},
    {id: 2, quizName: "meore", creator: "me"},
    {id: 3, quizName: "mesame", creator: "me"},
    {id: 4, quizName: "pirveli", creator: "me"},
    {id: 5, quizName: "meore", creator: "me"}
  ];
  
  const recQuizes = [
    {id: 1, quizName: "pirveli", creator: "meeee"},
    {id: 2, quizName: "meore", creator: "me"},
    {id: 3, quizName: "mesame", creator: "me"}
  ];


class Left extends Component {

    constructor() {
        super();
        this.state = {
          popQuizes: popQuizes,
          recQuizes: recQuizes,
          nOfTable: 0
          
        }
    }

    render() {
        return (
            <div className = "left">
                <div className = "profile">
                    <a href = "https://www.facebook.com" style = {{textDecoration:"none"}}>
                        <table className="profTable">
                        <tr>
                            <img className="picture" src="male.jpg"></img>
                            <p className = "nickName"><font size="4">vigaca</font></p>
                        </tr></table>
                    </a>
                </div>
                <div className="quizes">
                    <table className="quizesTable">
                        <thead>
                            <tr>
                                <th colSpan="3">
                                    <i onClick = {()=>changeTable(this, this.state.nOfTable)} className="leftArrow"></i>
                                    {this.state.nOfTable === 0 ? "Popular quizes" : " Recent quizes "}
                                    <i onClick = {()=>changeTable(this, this.state.nOfTable)}  className="rightArrow"></i>
                                </th>
                            </tr>
                        </thead>
                        <thead>
                            <tr>
                                <th>#</th>
                                <th>QuizName</th>
                                <th>Creator</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                            this.state.nOfTable === 0 ? 
                                (this.state.popQuizes.map((quiz) => {
                                    return <tr>
                                        <td><a href="https://www.facebook.com">{quiz.id}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.quizName}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.creator}</a></td>
                                    </tr>
                                }))
                            :
                                (this.state.recQuizes.map((quiz) => {
                                    return <tr>
                                        <td><a href="https://www.facebook.com">{quiz.id}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.quizName}</a></td>
                                        <td><a href="https://www.facebook.com">{quiz.creator}</a></td>
                                    </tr>
                                }))
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}



export default Left;