import { useEffect, useState } from "react";

function Post() {
    const [posts, setPosts] = useState([]);

    //hook 
    useEffect(() => {
        const getPosts = () => {
            //call the API to fetch all posts data 
            fetch('https://jsonplaceholder.typicode.com/posts')
                .then(response => response.json())
                .then(data => setPosts(data))
        }

        getPosts() //imp!!!!! 
    }, []);
    return (
        <div>
            <h2>All Posts using GET API</h2>
            {
                posts.map((p, index) => (
                    <div key={index}>
                        {p.id} <br />
                        {p.userId} <br />
                        {p.title} <br />
                        {p.body} <br />
                        <hr />
                    </div>
                ))
            }
        </div>
    )
}

export default Post;

//jsx = html + css + javascript 

/**
 * DOM: Document Object Model : web browser 
 * 
 * <div>
 *      <p></p>
 *      <p></p>
 * </div>
 * 
 * body 
 *    div
 *       p=5655656
 *       p=565776767 
 * 
 *   React User --> React DOM  --> Browser DOM 
 *  <div>
 *      <p key={p.id}></p>
 *      <p key={89898989}></p>
 * </div>
 * 
 */