import { useEffect, useState } from "react";

function Post() {
    const [posts, setPosts] = useState([]);

    //hook 
    useEffect(() => {
        const getPosts = () => {
            let post1 = {
                "userId": 1,
                "id": 1,
                "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
                "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
            }
            let post2 = {
                "userId": 1,
                "id": 2,
                "title": "qui est esse",
                "body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
            }
            //push post object to temp array 
            let temp = [];
            temp.push(post1);
            temp.push(post2);
            //assign this temp array to post array 
            setPosts(temp)

        }

        getPosts()
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