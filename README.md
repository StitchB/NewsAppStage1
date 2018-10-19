# News App Stage 1

Project #6 for Android Basics by Google Nanodegree Program

<h3>Layout</h3>
<table>
  <tr>
    <th>Criteria</th>
    <th>Meets Specifications</th>
  </tr>
  <tbody>
    <tr>
      <td>
        <p>Main Screen</p>
      </td>
      <td>
        <p>App contains a main screen which displays multiple news stories</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>List Item Contents</p>
      </td>
      <td>
        <p>Each list item on the main screen displays relevant text and information about the story.</p>
        <p>The title of the article and the name of the section that it belongs to are required field.</p>
        <p>If available, author name and date published should be included. Please note not all responses will contain these pieces of data, but it is required to include them if they are present.</p>
        <p>Images are not required.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Layout Best Practices</p>
      </td>
      <td>
        <p>The code adheres to all of the following best practices:</p>
        <ul>
          <li>Text sizes are defined in sp</li>
          <li>Lengths are defined in dp</li>
          <li>Padding and margin is used appropriately, such that the views are not crammed up against each other.</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

<h3>Funtionality</h3>
<table>
  <tr>
    <th>Criteria</th>
    <th>Meets Specifications</th>
  </tr>
  <tbody>
    <tr>
      <td>
        <p>Main Screen Updates</p>
      </td>
      <td>
        <p>Stories shown on the main screen update properly whenever new news data is fetched from the API.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Errors</p>
      </td>
      <td>
        <p>The code runs without errors.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Story Intents</p>
      </td>
      <td>
        <p>Clicking on a story uses an intent to open the story in the user’s browser.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>API Query</p>
      </td>
      <td>
        <p>App queries the content.guardianapis.com api to fetch news stories related to the topic chosen by the student, using either the ‘test’ api key or the student’s key.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>JSON Parsing</p>
      </td>
      <td>
        <p>The JSON response is parsed correctly, and relevant information is stored in the app.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>No Data Message</p>
      </td>
      <td>
        <p>When there is no data to display, the app shows a default TextView that informs the user how to populate the list.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Response Validation</p>
      </td>
      <td>
        <p>The app checks whether the device is connected to the internet and responds appropriately. The result of the request is validated to account for a bad server response or lack of server response.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Use of Loaders</p>
      </td>
      <td>
        <p>Networking operations are done using a Loader rather than an AsyncTask.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>External Libraries and Packages</p>
      </td>
      <td>
        <p>The intent of this project is to give you practice writing raw Java code using the necessary classes provided by the Android framework; therefore, the use of external libraries for the core functionality will not be permitted to complete this project.</p>
      </td>
    </tr>
  </tbody>
</table>

<h3>Code Readability</h3>
<table>
  <tr>
    <th>Criteria</th>
    <th>Meets Specifications</th>
  </tr>
  <tbody>
    <tr>
      <td>
        <p>Readability</p>
      </td>
      <td>
        <p>Code is easily readable such that a fellow programmer can understand the purpose of the app.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Naming Conventions</p>
      </td>
      <td>
        <p>All variables, methods, and resource IDs are descriptively named such that another developer reading the code can easily understand their function.</p>
      </td>
    </tr>
    <tr>
      <td>
        <p>Formatting</p>
      </td>
      <td>
        <p>The code is properly formatted i.e. there are no unnecessary blank lines; there are no unused variables or methods; there is no commented out code.</p>
      </td>
    </tr>
  </tbody>
</table>

<em>Requirements copied from: <a href="http://udacity.com">udacity.com</a>.</em>

<br />
<p align="center"> 
  <img src="showcase/news_app_stage_1.jpg" alt="Project Image 1">
</p>
