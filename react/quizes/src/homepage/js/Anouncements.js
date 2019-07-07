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
    constructor() {
        super();
        this.state = {
          anouncements: anouncements,
        }
    }


    render() {
        return (
            <div className = "anouncements">
                <table className="anoncTable">
                    <tbody>
                        {
                        this.state.anouncements.map((anons) => {
                            return <tr key={anons.id}>
                            <td><a style={{textDecoration:"none"}} href="https://www.facebook.com">{anons.statement}</a></td>
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

