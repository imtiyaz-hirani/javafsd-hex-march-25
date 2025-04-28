import { useSelector } from "react-redux";

function UserList() {

    const { users } = useSelector(state => state.users)

    return (
        <div>
            <h1>All Users</h1>
            {
                users.map((u, index) => (
                    <div key={index}>
                        <p>{u.name} --- {u.email}   &nbsp;&nbsp;&nbsp;
                            <button onClick={() => deleteUser(u.id)}>delete</button>  </p>
                    </div>
                ))
            }
        </div>
    )
}

export default UserList;