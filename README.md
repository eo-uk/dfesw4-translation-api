## Why are we doing this?
This is a translation database Rest API that can save any word pair in any language pair and store any specified field associated with that word pair. It allows looking up saved word pairs based on word, language, field or a combination of all.

## Endpoints
```
/api/words/add
Add new word pair

/api/words/update/{id}
Update word pair by id

/api/words
Get all word pairs

/api/words/word/{word}
Get word pair by word

/api/words/language/{language}
Get word by language

/api/words/id/{id}
Get word pair by id

/api/words/field/{field}
Get word pair by field

/api/words/search
Required Param: word
Optional Param: sourceLang
Optional Param: targetLang
Optional Param: field
Get word pairs based on language pairs and/or field

/api/text/translate
Required Param: text
Required Param: sourceLang
Required Param: targetLang
Optional Param: field
Get rudimentary literal translation of text based on the word pairs stored.
 
/api/words/delete/{id}
Delete word pair by id

/api/words/delete/all
Delete all word pairs
```

## How I expected the challenge to go
The project was expected to have more features at launch. One goal was to have support for conversion of text between various alphabets. It was also initially planned to have a working front-end as an extra.

## What went well? What didn't go as planned?
Many features had to be postponed until a future release. More than expected issues were encountered with testing and database connections. Maintaining GitHub hygiene was a priority and as such slowed down the development.

## Future Improvements
In the future, additional features can be added including a working front-end, user authentication, cloud-based AI processing of text, as well as the various extra features dropped to meet the deadline.

## Jira Board
<https://eouk.atlassian.net/jira/software/projects/DFETAPI/boards/2/roadmap>