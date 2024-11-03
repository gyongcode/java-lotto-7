package lotto.controller;

import java.util.ArrayList;
import lotto.Lotto;
import lotto.util.LottoManager;
import lotto.util.Saparater;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Controller {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    LottoManager lottoManager = new LottoManager();
    ArrayList<Lotto> lottos;

    public void start() {
        purchaseProcess();
        setWinningNumbersProcess();
        winningResultProcess();
    }

    public void purchaseProcess() {
        try {
            int amount = inputView.getPurchaseAmount();
            lottos = lottoManager.purchaseLotto(amount);
            outputView.printLottoInfo(lottos);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            purchaseProcess();
        }
    }

    public void setWinningNumbersProcess() {
        try {
            String input = inputView.getWinningNumbers();
            Saparater saparater = new Saparater(",");
            ArrayList<Integer> numbers = new ArrayList<>();
            for (String s : saparater.split(input)) {
                numbers.add(Integer.parseInt(s));
            }
            int bonusNumber = inputView.getBonusNumber();
            lottoManager.setWinningLotto(numbers, bonusNumber);
        } catch (NumberFormatException e){
            System.out.println("[ERROR] 숫자를 입력해주세요.");
            setWinningNumbersProcess();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            setWinningNumbersProcess();
        }
    }

    public void winningResultProcess() {
        outputView.printWinningResult(lottoManager.getWinningResult(lottos));
        outputView.printProfitRate(lottoManager.getProfitRate());
    }
}
