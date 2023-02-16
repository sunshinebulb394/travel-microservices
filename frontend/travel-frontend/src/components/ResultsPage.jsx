
function ResultsPage({ results }) {
    return (
      <div>
        <h2>Search Results</h2>
        <ul>
          {results.map((result) => (
            <li key={result.id}>{result.name}</li>
          ))}
        </ul>
      </div>
    );
  }
  
  export default ResultsPage;
  