import {Person} from "@/pages/api/person/all/person-all";
import {GetServerSideProps} from "next";

export default function PersonPage({repo}: {repo: Person[]}) {
  return (
      <div>
        <h1>Person Page</h1>
        {repo.map((person, index) => (
            <div key={index}>
              {person.firstName} {person.lastName} ({person.gender})
            </div>
        ))}
      </div>
  )
}

export const getServerSideProps: GetServerSideProps<{
  repo: Person[]
}> = async () => {
  const res = await fetch('http://localhost:8080/api/person/all')
  const repo = await res.json()
  return { props: { repo } }
}