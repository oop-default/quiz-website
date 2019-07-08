import React,{Component} from 'react';
import '../css/Categories.css'

const categories = [
    {id: 1, category: "imnairi"},
    {id :2, category: "magadi"}
];

class Categories extends Component{
    constructor() {
        super();
        this.state = {
          categories: categories
        }
    }

    render() {
        return (
            <div>
                <table className="categoriesTable">
                    <thead>
                        <th>Categories</th>
                    </thead>
                    <tbody>
                    {
                        this.state.categories.map((category) => {
                            return <tr key={category.id}>
                                <td onClick={() => window.location.href="https://www.facebook.com"}>
                                    {category.category}
                                </td>
                            </tr>
                        })
                    }
                    </tbody>
                </table>
            </div>
        )
    }

}

export default Categories;