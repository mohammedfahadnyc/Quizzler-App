package com.londonappbrewery.quizzler;

public class TrueFalse {
    private int mQuestionId;
    private boolean mAnswer;
    public TrueFalse (int questionResid, boolean trueOrFalse)
    {
        mQuestionId = questionResid;
        mAnswer = trueOrFalse;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
