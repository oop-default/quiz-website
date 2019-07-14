import React, { Component } from 'react';

import '../css/Anouncements.css'
const anouncements = [
    {id: 1, statement: "nothing for now"},
    {id: 2, statement: "same here"},
    {id: 3, statement: "nothing for now"},
    {id: 4, statement: "same here"},
    {id: 5, statement: "nothing for now"},
    {id: 6, statement: "same here"},
    {id: 7, statement: "nothing for now"},
    {id: 8, statement: "same here"},
    {id: 9, statement: "nothing for now"},
    {id: 0, statement: "same here"},
    {id: 10, statement: "nothing for now"},
    {id: 11, statement: "same here"},
    {id: 12, statement: "nothing for now"},
    {id: 13, statement: "same here"},
    {id: 14, statement: "nothing for now"},
    {id: 15, statement: "same here"},
    {id: 16, statement: "nothing for now"},
    {id: 17, statement: "same here"},
    {id: 18, statement: "nothing for now"},
    {id: 19, statement: "same here"},
    {id: 21, statement: "nothing for now"},
    {id: 22, statement: "same here"},
    {id: 23, statement: "nothing for now"},
    {id: 20, statement: "same here"}
  ];


  class Anouncements extends Component{
    constructor(props) {
        super();
        this.state = {
            isAdmin: props.isAdmin,
            anouncements: anouncements,
        }
    }


    render() {
        return (
            <div className = "anouncements">
                <table className="anoncTable">
                    <tbody>
                        {
                            this.state.isAdmin ? 
                                <tr>
                                    <td style={{display:""}}>
                                        <textarea className="postArea"></textarea>
                                        <button style={{cursor:"pointer"}}>POST</button>
                                    </td>
                                </tr>
                                :
                                null
                        }
                        {
                        this.state.anouncements.map((anons, i) => {
                            return <tr key={i}>
                            <td>{anons.statement}</td>
                            </tr>
                        })
                        }
                    </tbody>
                </table>
            </div>
        );
    }



  }

  export default Anouncements;

