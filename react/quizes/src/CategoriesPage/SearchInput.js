import React from 'react';



export function SearchInput() {
    return (
        <div class="wrap">
            <div className="search">
                <input type="text" id="searchInput" className="searchTerm" placeholder="What are you looking for?" />
                <button type="submit" onClick={() => this.search()} className="searchButton">
                    Search
                            </button>
            </div>
        </div>
        );
}