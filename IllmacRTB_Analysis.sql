USE sys;

SELECT *
FROM lyrics_1;
SELECT *
FROM lyrics_2;
SELECT *
FROM lyrics_3;
SELECT *
FROM lyrics_4;
SELECT *
FROM lyrics_5;
SELECT *
FROM lyrics_6;
SELECT *
FROM lyrics_7;
SELECT *
FROM lyrics_8;
SELECT *
FROM lyrics_9;
SELECT *
FROM lyrics_10;
SELECT *
FROM lyrics_11;
SELECT *
FROM lyrics_12;
SELECT *
FROM lyrics_13;
SELECT *
FROM lyrics_14;

SELECT COUNT(word) AS word_count
FROM lyrics_1
WHERE word = 'A';


SELECT COUNT(word) AS word_count
FROM lyrics_1
WHERE word = 'THE'
;

SELECT COUNT(word) AS word_count
FROM lyrics_2
WHERE word = 'THE'
;

/*
SELECT COUNT(L1.word) AS word_count_1,
		COUNT(L2.word) AS word_count_2
FROM lyrics_1 AS L1,
	(
		SELECT word
        FROM lyrics_2
        WHERE word = 'THE'
    ) AS L2
WHERE L1.word = 'THE'
;

SELECT L1.word AS words_1,
		L2.word AS words_2
FROM lyrics_1 AS L1,
	 lyrics_2 AS L2
;
*/

/*Find number of occurrences of a word on all lyric sets*/
SET @test_word = 'FLY';

SET @count1 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_1
WHERE word = @test_word
);
SELECT @count1;

SET @count2 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_2
WHERE word = @test_word
);
SELECT @count2;

SET @count3 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_3
WHERE word = @test_word
);
SELECT @count3;

SET @count4 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_4
WHERE word = @test_word
);
SELECT @count4;

SET @count5 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_5
WHERE word = @test_word
);
SELECT @count5;

SET @count6 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_6
WHERE word = @test_word
);
SELECT @count6;

SET @count7 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_7
WHERE word = @test_word
);
SELECT @count7;

SET @count8 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_8
WHERE word = @test_word
);
SELECT @count8;

SET @count9 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_9
WHERE word = @test_word
);
SELECT @count9;

SET @count10 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_10
WHERE word = @test_word
);
SELECT @count10;

SET @count11 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_11
WHERE word = @test_word
);
SELECT @count11;

SET @count12 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_12
WHERE word = @test_word
);
SELECT @count12;

SET @count13 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_13
WHERE word = @test_word
);
SELECT @count13;

SET @count14 = 
(
SELECT COUNT(word) AS word_count
FROM lyrics_14
WHERE word = @test_word
);
SELECT @count14;

SELECT @count1 + @count2 + @count3 + @count4 + @count5 + @count6 +
	   @count7 + @count8 + @count9 + @count10 + @count11 + @count12 +
       @count13 + @count14 
'Count of test_word in all tables';



/*******Count total words*******/
/***************************/
SET @word_count_1 =
(
	SELECT COUNT(word)
	FROM lyrics_1
);
SELECT @word_count_1;

/***************************/
SET @word_count_2 =
(
	SELECT COUNT(word)
	FROM lyrics_2
);
SELECT @word_count_2;

/***************************/
SET @word_count_3 =
(
	SELECT COUNT(word)
	FROM lyrics_3
);
SELECT @word_count_3;

/***************************/
SET @word_count_4 =
(
	SELECT COUNT(word)
	FROM lyrics_4
);
SELECT @word_count_4;

/***************************/
SET @word_count_5 =
(
	SELECT COUNT(word)
	FROM lyrics_5
);
SELECT @word_count_5;

/***************************/
SET @word_count_6 =
(
	SELECT COUNT(word)
	FROM lyrics_6
);
SELECT @word_count_6;

/***************************/
SET @word_count_7 =
(
	SELECT COUNT(word)
	FROM lyrics_7
);
SELECT @word_count_7;

/***************************/
SET @word_count_8 =
(
	SELECT COUNT(word)
	FROM lyrics_8
);
SELECT @word_count_8;

/***************************/
SET @word_count_9 =
(
	SELECT COUNT(word)
	FROM lyrics_9
);
SELECT @word_count_9;

/***************************/
SET @word_count_10 =
(
	SELECT COUNT(word)
	FROM lyrics_10
);
SELECT @word_count_10;

/***************************/
SET @word_count_11 =
(
	SELECT COUNT(word)
	FROM lyrics_11
);
SELECT @word_count_11;

/***************************/
SET @word_count_12 =
(
	SELECT COUNT(word)
	FROM lyrics_12
);
SELECT @word_count_12;

/***************************/
SET @word_count_13 =
(
	SELECT COUNT(word)
	FROM lyrics_13
);
SELECT @word_count_13;

/***************************/
SET @word_count_14 =
(
	SELECT COUNT(word)
	FROM lyrics_14
);
SELECT @word_count_14;

SELECT @word_count_1 + @word_count_2 + @word_count_3 + @word_count_4 + @word_count_5 +
		@word_count_6 + @word_count_7 + @word_count_8 + @word_count_9 + @word_count_10 +
        @word_count_11 + @word_count_12 + @word_count_13 + @word_count_14 AS total_word_count
        ;


/*Count unique words*/
/***************************/
SET @unique_count_1 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_1
	) AS l_dist
);
SELECT @unique_count_1;

/***************************/
SET @unique_count_2 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_2
	) AS l_dist
);
SELECT @unique_count_2;

/***************************/
SET @unique_count_3 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_3
	) AS l_dist
);
SELECT @unique_count_3;

/***************************/
SET @unique_count_4 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_4
	) AS l_dist
);
SELECT @unique_count_4;

/***************************/
SET @unique_count_5 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_5
	) AS l_dist
);
SELECT @unique_count_5;

/***************************/
SET @unique_count_6 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_6
	) AS l_dist
);
SELECT @unique_count_6;

/***************************/
SET @unique_count_7 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_7
	) AS l_dist
);
SELECT @unique_count_7;

/***************************/
SET @unique_count_8 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_8
	) AS l_dist
);
SELECT @unique_count_8;

/***************************/
SET @unique_count_9 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_9
	) AS l_dist
);
SELECT @unique_count_9;

/***************************/
SET @unique_count_10 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_10
	) AS l_dist
);
SELECT @unique_count_10;

/***************************/
SET @unique_count_11 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_11
	) AS l_dist
);
SELECT @unique_count_11;

/***************************/
SET @unique_count_12 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_12
	) AS l_dist
);
SELECT @unique_count_12;

/***************************/
SET @unique_count_13 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_13
	) AS l_dist
);
SELECT @unique_count_13;

/***************************/
SET @unique_count_14 =
(
	SELECT COUNT(word)
	FROM
	(
		SELECT DISTINCT word
		FROM lyrics_14
	) AS l_dist
);
SELECT @unique_count_14;


/*Vocab percent*/
SET @percent_1 =
(
	SELECT @unique_count_1 / @word_count_1
);
SELECT @percent_1;

SET @percent_2 =
(
	SELECT @unique_count_2 / @word_count_2
);
SELECT @percent_2;

SET @percent_3 =
(
	SELECT @unique_count_3 / @word_count_3
);
SELECT @percent_3;

SET @percent_4 =
(
	SELECT @unique_count_4 / @word_count_4
);
SELECT @percent_4;

SET @percent_5 =
(
	SELECT @unique_count_5 / @word_count_5
);
SELECT @percent_5;

SET @percent_6 =
(
	SELECT @unique_count_6 / @word_count_6
);
SELECT @percent_6;

SET @percent_7 =
(
	SELECT @unique_count_7 / @word_count_7
);
SELECT @percent_7;

SET @percent_8 =
(
	SELECT @unique_count_8 / @word_count_8
);
SELECT @percent_8;

SET @percent_9 =
(
	SELECT @unique_count_9 / @word_count_9
);
SELECT @percent_9;

SET @percent_10 =
(
	SELECT @unique_count_10 / @word_count_10
);
SELECT @percent_10;

SET @percent_11 =
(
	SELECT @unique_count_11 / @word_count_11
);
SELECT @percent_11;

SET @percent_12 =
(
	SELECT @unique_count_12 / @word_count_12
);
SELECT @percent_12;

SET @percent_13 =
(
	SELECT @unique_count_13 / @word_count_13
);
SELECT @percent_13;

SET @percent_14 =
(
	SELECT @unique_count_14 / @word_count_14
);
SELECT @percent_14;


/*Calculate average vocab percentage*/
SELECT (@percent_1 + @percent_2 + @percent_3 + @percent_4 + @percent_5 + @percent_6 +
	   @percent_7 + @percent_8 + @percent_9 + @percent_10 + @percent_11 + @percent_12 +
       @percent_13 + @percent_14) / 14 AS average_vocab_percent;


SELECT @percent_1, @percent_2, @percent_3, @percent_4, @percent_5, @percent_6,
	   @percent_7, @percent_8, @percent_9, @percent_10, @percent_11, @percent_12,
       @percent_13, @percent_14;
