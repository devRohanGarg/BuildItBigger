package com.udacity.gradle.builditbigger.javajoker;

import java.util.Random;

public class Joker {

    public static String tellJoke() {

        String[] jokes = {
                "Can a kangaroo jump higher than a house? Of course, a house doesn\"t jump at all.",
                "Doctor: \"I\"m sorry but you suffer from a terminal illness and have only 10 to live.\"\n" +
                        "\n" +
                        "Patient: \"What do you mean, 10? 10 what? Months? Weeks?!\"\n" +
                        "\n" +
                        "Doctor: \"Nine.\"",
                "A man asks a farmer near a field, \"Sorry sir, would you mind if I crossed your field instead of going around it? You see, I have to catch the 4:23 train.\"\n" +
                        "\n" +
                        "The farmer says, \"Sure, go right ahead. And if my bull sees you, you\"ll even catch the 4:11 one.\" ",
                "Police officer: \"Can you identify yourself, sir?\"\n" +
                        "\n" +
                        "Driver pulls out his mirror and says: \"Yes, it\"s me.\"",
                "\"How do you tell that a crab is drunk?\" It walks forwards.",
                "Do you know why women aren\"t allowed in space? \n" +
                        "-\n" +
                        "To avoid scenarios like: \"Houston, we have a problem!\" \n" +
                        "-\n" +
                        "\"What is the problem?\" \n" +
                        "-\n" +
                        "\"Yeah, great, pretend like you don\"t know what I\"m talking about!\"",
                "What would you call a very funny mountain? \n" +
                        "-\n" +
                        "\"Hill Arious\"\n",
                "Doctor says to his patient: \n" +
                        "\"You have Cancer and Alzheimer.\"\n" +
                        "-\n" +
                        "Patient: \"At least I don\"t have Cancer.\"",
                "A wife complains to her husband: \"Just look at that couple down the road, how lovely they are. He keeps holding her hand, kissing her, holding the door for her, why can\"t you do the same?\"\n" +
                        "\n" +
                        "\"Are you mad? I barely know the woman!\"",
                "Little Johnny asks his father: \n" +
                        "\"Where does the wind come from?\"\n" +
                        "-\n" +
                        "\"I don\"t know.\"\n" +
                        "-\n" +
                        "\"Why do dogs bark?\"\n" +
                        "-\n" +
                        "\"I don\"t know.\"\n" +
                        "-\n" +
                        "\"Why is the earth round?\"\n" +
                        "-\n" +
                        "\"I don\"t know.\"\n" +
                        "-\n" +
                        "\"Does it disturb you that I ask so much?\"\n" +
                        "-\n" +
                        "\"No son. Please ask. Otherwise you will never learn anything.\""};

        return (jokes[new Random().nextInt(jokes.length)]);
    }
}
