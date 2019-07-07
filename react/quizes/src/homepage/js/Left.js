import React,{Component} from 'react';
import '../css/Left.css';
import '../css/Arrows.css';
import {changeTable} from './functions';
import Categories from './Categories';


const popQuizes = [
    {id: 1, quizName: "pirveli", creatorId: 1, creator: "me"},
    {id: 2, quizName: "meore", creatorId: 2, creator: "me"},
    {id: 3, quizName: "mesame", creatorId: 3, creator: "me"},
    {id: 4, quizName: "pirveli", creatorId: 4, creator: "me"},
    {id: 5, quizName: "meore", creatorId: 5, creator: "me"}
  ];
  
  const recQuizes = [
    {id: 1, quizName: "pirveli", creatorId: 1, creator: "meeee"},
    {id: 2, quizName: "meore", creatorId: 2, creator: "me"},
    {id: 3, quizName: "mesame", creatorId: 3, creator: "me"},
    {id: 4, quizName: "pirveli", creatorId: 4, creator: "meeee"},
    {id: 5, quizName: "meore", creatorId: 5, creator: "me"},
    {id: 6, quizName: "pirveli", creatorId: 6, creator: "meeee"},
    {id: 7, quizName: "meore", creatorId: 7, creator: "me"},
    {id: 8, quizName: "pirveli", creatorId: 8, creator: "meeee"},
    {id: 9, quizName: "meore", creatorId: 9, creator: "me"},
    {id: 0, quizName: "pirveli", creatorId: 10, creator: "meeee"},
    {id: 21, quizName: "meore", creatorId: 11, creator: "me"},
    {id: 12, quizName: "pirveli", creatorId: 12, creator: "meeee"},
    {id: 23, quizName: "meore", creatorId: 13, creator: "me"},
    {id: 14, quizName: "pirveli", creatorId: 14, creator: "meeee"},
    {id: 25, quizName: "meore", creatorId: 15, creator: "me"},
    {id: 16, quizName: "pirveli", creatorId: 16, creator: "meeee"},
    {id: 27, quizName: "meore", creatorId: 17, creator: "me"},
    {id: 19, quizName: "pirveli", creatorId: 18, creator: "meeee"},
    {id: 20, quizName: "meore", creatorId: 19, creator: "me"}
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
                    <a href = {"profile/:userId"} style = {{textDecoration:"none"}}>
                        <table className="profTable">
                            <tbody>
                                <tr>
                                    <td>
                                        <img className="picture" src="male.jpg"></img>
                                        <p className = "nickName"><font size="4">vigaca</font></p>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
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
                                    return <tr key={quiz.id}>
                                        <td><a href={"/quiz/:quizId"}>{quiz.id}</a></td>
                                        <td><a href={"/quiz/:quizId"}>{quiz.quizName}</a></td>
                                        <td><a href={"/quiz/:quizId"}>{quiz.creator}</a></td>
                                    </tr>
                                }))
                            :
                                (this.state.recQuizes.map((quiz) => {
                                    return <tr key={quiz.id}>
                                        <td><a href={"/quiz/:quizId"}>{quiz.id}</a></td>
                                        <td><a href={"/quiz/:quizId"}>{quiz.quizName}</a></td>
                                        <td><a href={"/quiz/:quizId"}>{quiz.creator}</a></td>
                                    </tr>
                                }))
                            }
                        </tbody>
                    </table>
                </div>
                <Categories></Categories>
            </div>
        );
    }
}



export default Left;